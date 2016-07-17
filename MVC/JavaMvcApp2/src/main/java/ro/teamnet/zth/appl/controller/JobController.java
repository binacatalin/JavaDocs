package ro.teamnet.zth.appl.controller;

import ro.teamnet.zth.api.annotations.MyController;
import ro.teamnet.zth.api.annotations.MyRequestMethod;
import ro.teamnet.zth.api.annotations.MyRequestParam;
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

    /**
     * @return
     */
    @MyRequestMethod(urlPath = "/one")
    public Job getOneJob(@MyRequestParam(paramName = "id") Long id) {
        return jobService.findOneJob(id);
    }

    @MyRequestMethod(urlPath = "/delete", methodType = "DELETE")
    public String deleteOneJob(@MyRequestParam(paramName = "id") Long id) {
        jobService.deleteOneJob(id);
        return "Delete done";
    }

    @MyRequestMethod(urlPath = "/save", methodType = "POST")
    public Job saveOneJob(@MyRequestParam(paramName = "id") Long id) {
        Job job = new Job();
        job.setId(id.toString());

        return jobService.saveJob(job);
    }
}
