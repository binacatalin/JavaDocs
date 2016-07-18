package ro.teamnet.zth.appl.service.impl;

import ro.teamnet.zth.api.annotations.MyRequestMethod;
import ro.teamnet.zth.api.annotations.MyRequestParam;
import ro.teamnet.zth.appl.dao.JobDao;
import ro.teamnet.zth.appl.domain.Job;
import ro.teamnet.zth.appl.service.JobService;

import java.util.List;

/**
 * Created by user on 7/15/2016.
 */
public class JobServiceImpl implements JobService {
    private final JobDao jobDao = new JobDao();

    @Override
    public List<Job> findAllJobs() {
        return jobDao.getAllJobs();
    }

    @Override
    public Job findOneJob(String id) {
        return jobDao.getJobById(id);
    }

    @Override
    public void deleteOneJob(String id) {
        jobDao.deleteJob(jobDao.getJobById(id));
    }

    @Override
    public Job saveJob(Job job) {
        return jobDao.insertJob(job);
    }

    @Override
    public Job editJob(Job job) {
//        Job newJob = jobDao.getJobById(job.getId());
//        if (newJob != null)
//            return jobDao.updateJob(job).toString();

        return jobDao.updateJob(job);
    }
}
