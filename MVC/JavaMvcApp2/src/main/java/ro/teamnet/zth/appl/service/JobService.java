package ro.teamnet.zth.appl.service;

import ro.teamnet.zth.appl.domain.Job;

import java.util.List;

/**
 * Created by user on 7/15/2016.
 */
public interface JobService {
    List<Job> findAllJobs();
    Job findOneJob(Long id);
    void deleteOneJob(Long id);
    Job saveJob(Job job);
}
