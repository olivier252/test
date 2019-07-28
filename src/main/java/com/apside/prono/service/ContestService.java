package com.apside.prono.service;

import javax.transaction.Transactional;

import com.apside.prono.errors.InvalidContestDataException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.apside.prono.model.Contest;
import com.apside.prono.repository.ContestRepository;

@Service

public class ContestService {

    @Autowired
    private ContestRepository contestRepository;

    public Iterable<Contest> getAllContest() {
        return contestRepository.findAll();
    }

    @Transactional
    public void createContest(Contest contest) {

        if (contest.getLabel() == null) {
            throw new InvalidContestDataException();
        } else {
            contestRepository.save(contest);
        }
    }

    public Contest getContestById(Long id) {
        return contestRepository.findById(id).get();
    }

    @Transactional
    public Contest modifyContest(Contest contest) {
        Contest contestModif = new Contest();
        if (contestRepository.existsById(contest.getId())) {
            contestModif = getContestById(contest.getId());
        } else {
            throw new InvalidContestDataException();
        }

        if (contest.getLabel() == null) {
            throw new InvalidContestDataException();
        } else {
            contestModif.setId(contest.getId());
            contestModif.setLabel(contest.getLabel());
        }
        return contestModif;
    }

    @Transactional
    public void deleteContest(Long id) {
        if (contestRepository.existsById(id)) {
            contestRepository.deleteById(id);
        } else {
            throw new InvalidContestDataException();
        }
    }
}

