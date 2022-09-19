package voting.app.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import voting.app.dao.ApplicationPropertyRepository;
import voting.app.enums.VotingResultStatus;
import voting.app.enums.VotingStatus;
import voting.app.models.ApplicationProperty;
import voting.app.models.Candidate;

import javax.annotation.PostConstruct;
import java.util.List;

@Service
public class ApplicationPropertyServiceImpl {
    @Autowired
    private ApplicationPropertyRepository applicationPropertyRepository;

    public static final String VOTING_RESULT_STATUS_QUALIFIER = "voting.result.status";
    public static final String VOTING_STATUS_QUALIFIER = "voting.status";

    @PostConstruct
    public void insertAppProperties() {
        List<ApplicationProperty> applicationPropertiesList1 = applicationPropertyRepository.findByName(VOTING_RESULT_STATUS_QUALIFIER);
        if (applicationPropertiesList1.isEmpty()) {
            ApplicationProperty applicationProperty = new ApplicationProperty();
            applicationProperty.setName(VOTING_RESULT_STATUS_QUALIFIER);
            applicationProperty.setValue(VotingResultStatus.NOT_DECLARED.toString());
            applicationPropertyRepository.save(applicationProperty);
        }
        List<ApplicationProperty> applicationPropertiesList2 = applicationPropertyRepository.findByName(VOTING_STATUS_QUALIFIER);
        if (applicationPropertiesList2.isEmpty()) {
            ApplicationProperty applicationProperty = new ApplicationProperty();
            applicationProperty.setName(VOTING_STATUS_QUALIFIER);
            applicationProperty.setValue(VotingStatus.NOT_STARTED.toString());
            applicationPropertyRepository.save(applicationProperty);
        }
    }

    public void updateVotingResultStatus(VotingResultStatus votingResultStatus) {
        List<ApplicationProperty> applicationPropertiesList = applicationPropertyRepository.findByName(VOTING_RESULT_STATUS_QUALIFIER);
        ApplicationProperty applicationProperty = null;
        if (!applicationPropertiesList.isEmpty()) {
            applicationProperty = applicationPropertiesList.get(applicationPropertiesList.size()-1);
        } else {
            applicationProperty = new ApplicationProperty();
            applicationProperty.setName(VOTING_RESULT_STATUS_QUALIFIER);
        }
        applicationProperty.setValue(votingResultStatus.toString());
        applicationPropertyRepository.save(applicationProperty);
    }

    public void updateVotingStatus(VotingStatus votingStatus) {
        List<ApplicationProperty> applicationPropertiesList = applicationPropertyRepository.findByName(VOTING_STATUS_QUALIFIER);
        ApplicationProperty applicationProperty = null;
        if (!applicationPropertiesList.isEmpty()) {
            applicationProperty = applicationPropertiesList.get(applicationPropertiesList.size()-1);
        } else {
            applicationProperty = new ApplicationProperty();
            applicationProperty.setName(VOTING_STATUS_QUALIFIER);
        }
        applicationProperty.setValue(votingStatus.toString());
        applicationPropertyRepository.save(applicationProperty);
    }

    public VotingStatus votingStatus() {
        List<ApplicationProperty> applicationPropertiesList = applicationPropertyRepository.findByName(VOTING_STATUS_QUALIFIER);
        ApplicationProperty applicationProperty = null;
        if (!applicationPropertiesList.isEmpty()) {
            applicationProperty = applicationPropertiesList.get(applicationPropertiesList.size()-1);
        } else {
            applicationProperty = new ApplicationProperty();
            applicationProperty.setName(VOTING_STATUS_QUALIFIER);
            applicationProperty.setValue(VotingStatus.NOT_STARTED.toString());
            applicationPropertyRepository.save(applicationProperty);
        }
        return VotingStatus.valueOf(applicationProperty.getValue());
    }

    public VotingResultStatus votingResultStatus() {
        List<ApplicationProperty> applicationPropertiesList = applicationPropertyRepository.findByName(VOTING_RESULT_STATUS_QUALIFIER);
        ApplicationProperty applicationProperty = null;
        if (!applicationPropertiesList.isEmpty()) {
            applicationProperty = applicationPropertiesList.get(applicationPropertiesList.size()-1);
        } else {
            applicationProperty = new ApplicationProperty();
            applicationProperty.setName(VOTING_RESULT_STATUS_QUALIFIER);
            applicationProperty.setValue(VotingResultStatus.NOT_DECLARED.toString());
            applicationPropertyRepository.save(applicationProperty);
        }
        return VotingResultStatus.valueOf(applicationProperty.getValue());
    }

    public List<ApplicationProperty> getAllApplicationProperties() {
        List<ApplicationProperty> applicationPropertiesList = applicationPropertyRepository.findAll();
        return applicationPropertiesList;
    }
}
