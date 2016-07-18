package ro.teamnet.zth.appl.service;

import ro.teamnet.zth.appl.dao.JobDao;
import ro.teamnet.zth.appl.domain.Job;

import java.util.List;

/**
 * Created by user on 7/15/2016.
 */
public class JobServiceimpl implements JobService {
    private final JobDao jobDao = new JobDao();

    @Override
    public List<Job> findAllJobs() {
        return jobDao.getAllJobs();
    }

    @Override
    public Job findOneJob(Long id) {
        return jobDao.getJobById(id.toString());
    }

    @Override
    public void deleteOneJob(Long id) {
        jobDao.deleteJob(jobDao.getJobById(id.toString()));
    }

    @Override
    public Job saveJob(Job job) {
        return jobDao.insertJob(job);
    }
}
