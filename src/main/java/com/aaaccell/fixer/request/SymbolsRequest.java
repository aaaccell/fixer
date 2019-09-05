package com.aaaccell.fixer.request;

import com.aaaccell.fixer.FixerService;
import com.aaaccell.fixer.models.SymbolsResponse;
import retrofit2.Call;

public class SymbolsRequest extends AuthenticatedRequest<SymbolsResponse> {

    public SymbolsRequest(FixerService fixerService, String accessKey) {
        super(fixerService, accessKey);
    }

    @Override
    Call<SymbolsResponse> getCall() {
        return fixerService.getSymbols(accessKey);
    }

}
