package ftn.upp.invertAuction.dto;

public class StartIADTO {

    private String job_category;
    private String job_description;
    private String job_max_price;
    private String job_application_deadline;
    private String job_min_candidates;
    private String job_max_candidates;
    private String job_deadline;

    public StartIADTO(String job_category, String job_description, String job_max_price, String job_application_deadline, String job_max_candidates, String job_min_candidates, String job_deadline) {
        this.job_category = job_category;
        this.job_description = job_description;
        this.job_max_price = job_max_price;
        this.job_application_deadline = job_application_deadline;
        this.job_min_candidates = job_min_candidates;
        this.job_max_candidates = job_max_candidates;
        this.job_deadline = job_deadline;
    }

    public StartIADTO() {
    }

    public String getJob_max_candidates() {
        return job_max_candidates;
    }

    public void setJob_max_candidates(String job_max_candidates) {
        this.job_max_candidates = job_max_candidates;
    }

    public String getJob_category() {
        return job_category;
    }

    public void setJob_category(String job_category) {
        this.job_category = job_category;
    }

    public String getJob_description() {
        return job_description;
    }

    public void setJob_description(String job_description) {
        this.job_description = job_description;
    }

    public String getJob_max_price() {
        return job_max_price;
    }

    public void setJob_max_price(String job_max_price) {
        this.job_max_price = job_max_price;
    }

    public String getJob_application_deadline() {
        return job_application_deadline;
    }

    public void setJob_application_deadline(String job_application_deadline) {
        this.job_application_deadline = job_application_deadline;
    }

    public String getJob_min_candidates() {
        return job_min_candidates;
    }

    public void setJob_min_candidates(String job_min_candidates) {
        this.job_min_candidates = job_min_candidates;
    }

    public String getJob_deadline() {
        return job_deadline;
    }

    public void setJob_deadline(String job_deadline) {
        this.job_deadline = job_deadline;
    }
}

