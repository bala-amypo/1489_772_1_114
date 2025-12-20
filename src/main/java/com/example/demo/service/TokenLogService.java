package com.example.demo.service;

import com.example.demo.entity.TokenLog;
import java.util.List;

public interface TokenLogService {

    TokenLog saveLog(TokenLog tokenLog);

    List<TokenLog> getLogsByTokenId(Long tokenId);
}
