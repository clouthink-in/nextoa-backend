package in.clouthink.synergy.passcode.repository;


import in.clouthink.synergy.passcode.model.Passcode;
import in.clouthink.synergy.shared.repository.AbstractRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PasscodeRepository extends AbstractRepository<Passcode> {

	Passcode findByCellphoneAndCategory(String cellphone, String category);

}
