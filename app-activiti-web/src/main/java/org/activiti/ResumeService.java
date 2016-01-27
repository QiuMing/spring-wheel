package org.activiti;

import org.springframework.stereotype.Component;

@Component
public class ResumeService {

    public ResumeService(){
        System.err.println("+++++++++++++");
    }
    public void storeResume() {
        System.out.println("Storing resume ...");
    }

}
