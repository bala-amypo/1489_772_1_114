package com.example.demo.service;

import java.util.List;
import com.example.demo.entity.TokenLog;

public interface TokenLogService {

    TokenLog saveLog(TokenLog tokenLog);

    List<TokenLog> getLogsByTokenId(Long tokenId);
}
