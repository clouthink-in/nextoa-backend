package in.clouthink.nextoa.passcode.repository;


import in.clouthink.nextoa.passcode.model.Passcode;
import in.clouthink.nextoa.shared.repository.AbstractRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PasscodeRepository extends AbstractRepository<Passcode> {

	Passcode findByCellphoneAndCategory(String cellphone, String category);

}
