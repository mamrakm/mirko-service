package cz.ememsoft.micro.service.repository;

import lombok.RequiredArgsConstructor;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

@Component
@RequiredArgsConstructor
public class SequenceProviderImpl implements SequenceProvider {

  public final static String QUERY = "select nextval('account_sequence')";

  private final JdbcTemplate jdbcTemplate;

  @Override
  @Transactional(readOnly = true)
  public String next() {
    return String.valueOf(jdbcTemplate.queryForObject(QUERY, Long.class));
  }
}
