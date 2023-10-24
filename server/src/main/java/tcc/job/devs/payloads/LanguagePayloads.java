package tcc.job.devs.payloads;

import lombok.Data;

public class LanguagePayloads {

    @Data
    public static class LanguagePayload {
        private int id;
        private String name;
    }

}
