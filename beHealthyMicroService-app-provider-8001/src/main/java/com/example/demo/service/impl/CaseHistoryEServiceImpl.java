package com.example.demo.service.impl;

import com.example.demo.dao.CaseHistoryRepository;
import com.example.demo.entity.CaseHistory;
import com.example.demo.service.CaseHistoryService;
import org.elasticsearch.action.search.SearchResponse;
import org.elasticsearch.client.transport.TransportClient;
import org.elasticsearch.common.lucene.search.MoreLikeThisQuery;
import org.elasticsearch.common.settings.Settings;
import org.elasticsearch.common.transport.TransportAddress;
import org.elasticsearch.index.query.BoolQueryBuilder;
import org.elasticsearch.index.query.MoreLikeThisQueryBuilder;
import org.elasticsearch.index.query.QueryBuilders;
import org.elasticsearch.index.query.TermQueryBuilder;
import org.elasticsearch.search.SearchHit;
import org.elasticsearch.transport.client.PreBuiltTransportClient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.net.InetAddress;
import java.net.UnknownHostException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

@Service
public class CaseHistoryEServiceImpl implements CaseHistoryService {
    @Autowired
    private CaseHistoryRepository caseHistoryRepository;
    @Override
    public List<CaseHistory> getAll(Integer uid, Integer fid) {
        List<CaseHistory> list = new ArrayList<>();
        Iterable<CaseHistory> iterable =  caseHistoryRepository.findAll();
        Iterator<CaseHistory> iterator  = iterable.iterator();
        while (iterator.hasNext()){
            list.add(iterator.next());
        }
        return list;
    }

    @Override
    public List<CaseHistory> getAll(Integer uid, Integer fid, String query) {
        //模糊查询
        MoreLikeThisQueryBuilder builder = QueryBuilders.moreLikeThisQuery(
                new String[]{"visitName","hospitalName","office","doctorName","checkup","diagnosisResult","mainSuit","historyNow","suggestion"},
                new String[] {query},null);//如果不指定filedName，则默认全部，常用在相似内容的推荐上
         //匹配查询
        TermQueryBuilder uidBuilder = QueryBuilders.termQuery("uid",uid);
        TermQueryBuilder fidBuilder = QueryBuilders.termQuery("fid",fid);
        BoolQueryBuilder boolQueryBuilder = new BoolQueryBuilder();
        boolQueryBuilder.must(uidBuilder);
        boolQueryBuilder.must(fidBuilder);
        boolQueryBuilder.must(builder);
        Iterable<CaseHistory> iterable= caseHistoryRepository.search(boolQueryBuilder);
        List<CaseHistory> list = new ArrayList<>();
        Iterator<CaseHistory> iterator  = iterable.iterator();
        while (iterator.hasNext()){
            list.add(iterator.next());
        }
        return list;
    }

    @Override
    public CaseHistory findById(Integer cid) {
        return null;
    }

    @Override
    public Integer addCaseHis(CaseHistory caseHistory) {
        caseHistory =  caseHistoryRepository.save(caseHistory);
        return caseHistory.getCid();
    }

    @Override
    public Integer updateCaseHis(CaseHistory caseHistory) {
        int num = 1;
        try {
            caseHistoryRepository.save(caseHistory);
        }catch (Exception e){
            num = 0;
        }
        return num;
    }

    @Override
    public Integer deleteCaseHis(Integer cid) {
        int num = 1;
        try {
            caseHistoryRepository.deleteById(cid);
        }catch (Exception e){
            num = 0;
        }
        return num;
    }
}
