package ro.teamnet.zth.appl.service;

import ro.teamnet.zth.appl.dao.JobDao;
import ro.teamnet.zth.appl.domain.Job;

import java.util.List;

/**
 * Created by user on 7/15/2016.
 */
public class JobServiceimpl implements JobService {
    @Override
    public List<Job> findAllJobs() {
        JobDao jobDao = new JobDao();

        return jobDao.getAllJobs();
    }
}
