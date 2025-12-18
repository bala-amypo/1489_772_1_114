@PostMapping("/token-logs")
public TokenLog createTokenLog(@RequestBody TokenLog tokenLog) {
    // 1. Get the Token ID from the incoming JSON
    Long tokenId = tokenLog.getToken().getId();

    // 2. Fetch the real Token entity from DB
    Token managedToken = tokenRepository.findById(tokenId)
            .orElseThrow(() -> new RuntimeException("Token not found with id: " + tokenId));

    // 3. Set the managed Token to TokenLog
    tokenLog.setToken(managedToken);

    // 4. Set loggedAt timestamp
    tokenLog.setLoggedAt(LocalDateTime.now());

    // 5. Save the TokenLog
    return tokenLogRepository.save(tokenLog);
}
