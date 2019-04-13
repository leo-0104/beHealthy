package com.example.demo.dao;

import com.example.demo.entity.CaseHistory;
import org.springframework.data.elasticsearch.repository.ElasticsearchRepository;

public interface CaseHistoryRepository extends ElasticsearchRepository<CaseHistory,Integer> {
}
