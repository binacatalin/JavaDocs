package ro.teamnet.zth.appl.controller;

//import com.google.gson.Gson;

import ro.teamnet.zth.api.annotations.MyController;
import ro.teamnet.zth.api.annotations.MyRequestMethod;
import ro.teamnet.zth.api.annotations.MyRequestObject;
import ro.teamnet.zth.api.annotations.MyRequestParam;
//import org.json.simple.JSONObject;
import ro.teamnet.zth.appl.domain.Job;
import ro.teamnet.zth.appl.service.JobService;
import ro.teamnet.zth.appl.service.impl.JobServiceImpl;

import java.util.List;

/**
 * Created by user on 7/15/2016.
 */
@MyController(urlPath = "/jobs")
public class JobController {
    private final JobService jobService = new JobServiceImpl();

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
    public Job getOneJob(@MyRequestParam(paramName = "id") String id) {
        return jobService.findOneJob(id);
    }

    @MyRequestMethod(urlPath = "/delete", methodType = "DELETE")
    public String deleteOneJob(@MyRequestParam(paramName = "id") String id) {
        jobService.deleteOneJob(id);
        return "Delete done";
    }

    @MyRequestMethod(urlPath = "/edit", methodType = "PUT")
    public String updateOneJob(@MyRequestObject Job job) {
//        jobService.editJob(job);
        if (job != null)
            return jobService.editJob(job).toString();

        return "Update faild";
    }

    @MyRequestMethod(urlPath = "/save", methodType = "POST")
    public String saveOneJob(@MyRequestObject Job job) {
        if (job != null)
            return jobService.saveJob(job).toString();

        return "Save faild";
    }
}
