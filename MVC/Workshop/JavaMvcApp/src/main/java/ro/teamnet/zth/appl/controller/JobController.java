package ro.teamnet.zth.appl.controller;

import ro.teamnet.zth.api.annotations.MyController;
import ro.teamnet.zth.api.annotations.MyRequestMethod;
import ro.teamnet.zth.appl.domain.Job;
import ro.teamnet.zth.appl.service.JobService;
import ro.teamnet.zth.appl.service.JobServiceimpl;

import java.util.List;

/**
 * Created by user on 7/15/2016.
 */
@MyController(urlPath = "/jobs")
public class JobController {
    private final JobService jobService = new JobServiceimpl();

    /**
     * @return
     */
    @MyRequestMethod(urlPath = "/all")
    public List<Job> getAllJobs() {
        return jobService.findAllJobs();
    }
}
