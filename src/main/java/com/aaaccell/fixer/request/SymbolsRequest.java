package com.aaaccell.fixer.request;

import com.aaaccell.fixer.FixerService;
import com.aaaccell.fixer.response.SymbolsResponse;
import lombok.EqualsAndHashCode;
import retrofit2.Call;

@EqualsAndHashCode(callSuper = true)
public class SymbolsRequest extends AuthenticatedRequest<SymbolsResponse> {

    public SymbolsRequest(FixerService fixerService, String accessKey) {
        super(fixerService, accessKey);
    }

    @Override
    Call<SymbolsResponse> getCall() {
        return fixerService.getSymbols(accessKey);
    }

}
