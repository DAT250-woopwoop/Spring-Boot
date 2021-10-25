package no.hvl.dat250.feedApp.dto;

import lombok.*;

@Getter
@Setter
public class PollCreationDTO {
        private String pollDesc;
        private String pollName;
        private String startTime;
        private String endTime;
        private boolean privatePoll;
        private boolean closed;
        private int yesOption;
        private int noOption;
}
