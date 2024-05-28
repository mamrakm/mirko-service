package cz.ememsoft.micro.service.service;

import cz.ememsoft.micro.service.api.CreateSubjectRequest;
import cz.ememsoft.micro.service.api.SubjectResponse;
import cz.ememsoft.micro.service.client.PrefixClient;
import cz.ememsoft.micro.service.domain.BankAccount;
import cz.ememsoft.micro.service.mapper.SubjectMapper;
import cz.ememsoft.micro.service.repository.BankAccountRepository;
import cz.ememsoft.micro.service.repository.SubjectRepository;
import cz.ememsoft.micro.service.repository.SequenceProvider;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Component
@AllArgsConstructor
@Slf4j
public class SubjectServiceImpl implements SubjectService {

    private final SubjectMapper subjectMapper;
    private final SubjectRepository subjectRepository;
    private final BankAccountRepository bankAccountRepository;
    private final PrefixClient prefixClient;
    private final SequenceProvider sequenceProvider;
    private BankAccountService bankAccountService;

    @Override
    @Transactional
    public Long save(CreateSubjectRequest request) {
        var prefixClientResponseBody = Optional.ofNullable(prefixClient.getPrefix().getBody());
        var prefix = prefixClientResponseBody.map(PrefixClient.Prefix::getPrefix).orElse(null);

        var suffix = sequenceProvider.next();

        BankAccount bankAccount = new BankAccount();
        bankAccount.setPrefix(prefix);
        bankAccount.setSuffix(suffix);

        bankAccountRepository.saveAndFlush(bankAccount);
        return subjectRepository.saveAndFlush(subjectMapper.map(request)).getId();
    }

    @Override
    @Transactional(readOnly = true)
    public Optional<SubjectResponse> findById(Long id) {
        return subjectRepository.findById(id)
                .map(db -> {
                    var mapped = subjectMapper.map(db);
                    mapped.setNumberOfAccounts(bankAccountRepository.numberOfAccounts(db.getId()));

                    return mapped;
                });
    }

    @Override
    @Transactional(readOnly = true)
    public List<SubjectResponse> subjectsWithLowBalance() {
        return subjectRepository.getSubjectsWithLowBalance()
                .stream()
                .map(subjectMapper::map)
                .collect(Collectors.toList());
    }
}
