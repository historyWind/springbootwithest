package com.example.springbootwithestest.esTestOne;

import com.example.springbootwithestest.esTestOne.entity.Goods;
import com.example.springbootwithestest.esTestOne.repository.GoodsRepository;
import org.elasticsearch.index.query.*;
import org.elasticsearch.index.query.functionscore.FunctionScoreQueryBuilder;
import org.elasticsearch.index.search.MatchQuery;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.elasticsearch.core.ElasticsearchTemplate;
import org.springframework.data.elasticsearch.core.query.NativeSearchQuery;
import org.springframework.data.elasticsearch.core.query.NativeSearchQueryBuilder;
import org.springframework.data.elasticsearch.core.query.SearchQuery;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Optional;

import static org.elasticsearch.index.query.QueryBuilders.matchQuery;

/**
 * @author lcb
 * @date 2018/11/16 11:35
 */
@RestController
@RequestMapping("/goods")
public class GoodsController {
    @Autowired
    private ElasticsearchTemplate elasticsearchTemplate;
    @Autowired
    private GoodsRepository goodsRepository;

    @RequestMapping("/save")
    public String save(){
        Goods goods = new Goods(1L,"kele","饮料",1);
        Goods save = goodsRepository.save(goods);
        return  save.getId().toString();
    }

    @RequestMapping("/delete")
    public String delete(long id){
        goodsRepository.deleteById(id);
        return  "success";
    }

    @RequestMapping("/getOne")
    public String getOne(long id){
        Optional<Goods> byId = goodsRepository.findById(id);
        return byId.toString();
    }

    private int pagesize =10;
    public List<Goods> getList(Integer pageNumber,String query){
        if(pageNumber ==null) pageNumber =0;
        return null;
    }

    @RequestMapping("/getEntitySearchQuery")
    public Object getEntitySearchQuery(Integer pageNumber,Integer pageSize,String searchContent){
        // 设置分页（ES6（PageRequest.of）与之前版本不一致new PageRequest()）
        Page<Goods> goodsPage = goodsRepository.findAll(PageRequest.of(0, 20));
        List<Goods> content = goodsPage.getContent();
        return content;
    }


    @RequestMapping("/searchTest")
    @ResponseBody
    public String searchTest(String name,String desc,Float price){
        // 如果name和price二个参数都有，那么以二个参数一起查询（相当于findByNameAndPrice），如果有一个参数为空，那么只用一个参数查询（相当于findByName）
        List<Goods> goodList   =  goodsRepository.findByNameAndPrice( name, price);
        List<Goods> goodList1  =  goodsRepository.findByNameOrPrice( name,  price);
        List<Goods> goodList2  =  goodsRepository.findByName( name);
        List<Goods> goodList3  =  goodsRepository.findByNameNot( name);
        List<Goods> goodList4  =  goodsRepository.findByNameAndDescription( name, desc);
        List<Goods> goodList5 = goodsRepository.findTest(name,desc);
        System.out.println( " goodList: " +goodList);
        System.out.println();
        System.out.println( " goodList1: " +goodList1);
        System.out.println();
        System.out.println( " goodList2: " +goodList2);
        System.out.println();
        System.out.println( " goodList3: " +goodList3);
        System.out.println();
        System.out.println( " goodList4: " +goodList4);
        System.out.println();
        System.out.println( " goodList5: " +goodList5);
        return "goodList: "+ goodList + " goodList1: " +goodList1 + " goodList2: " +goodList2 + " goodList3: " +goodList3 + " goodList4: " +goodList4;
    }

    @RequestMapping("/searchPageByBuilder")
    public Object searchPageByBuilder(String name,String desc,Long price){
        FuzzyQueryBuilder queryBuilder = QueryBuilders.fuzzyQuery("name",name);
        // 设置分页（ES6（PageRequest.of）与之前版本不一致new PageRequest()）
        PageRequest pageRequest = PageRequest.of(0, 2);
        NativeSearchQueryBuilder nativeSearchQueryBuilder = new NativeSearchQueryBuilder();
        nativeSearchQueryBuilder.withPageable(pageRequest);
        nativeSearchQueryBuilder.withQuery(queryBuilder);
        NativeSearchQuery build = nativeSearchQueryBuilder.build();
        Page<Goods> goodsPage = goodsRepository.search(build);
        return  goodsPage;
    }
}
