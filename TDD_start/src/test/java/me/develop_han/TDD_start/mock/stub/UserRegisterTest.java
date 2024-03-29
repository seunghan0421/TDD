package me.develop_han.TDD_start.mock.stub;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import me.develop_han.TDD_start.mock.stub.domain.MemoryUserRepository;
import me.develop_han.TDD_start.mock.stub.domain.SpyEmailNotifier;
import me.develop_han.TDD_start.mock.stub.domain.StubWeakPasswordChecker;
import me.develop_han.TDD_start.mock.stub.domain.User;
import me.develop_han.TDD_start.mock.stub.domain.UserRegister;
import me.develop_han.TDD_start.mock.stub.exception.DupidExeption;
import me.develop_han.TDD_start.mock.stub.exception.WeakPasswordException;

public class UserRegisterTest {
	private UserRegister userRegister;
	private StubWeakPasswordChecker stubWeakPasswordChecker = new StubWeakPasswordChecker();
	private MemoryUserRepository fakeRepository = new MemoryUserRepository();
	private SpyEmailNotifier spyEmailNotifier = new SpyEmailNotifier();

	@BeforeEach
	void setUp() {
		userRegister = new UserRegister(stubWeakPasswordChecker, fakeRepository, spyEmailNotifier);
	}

	@DisplayName("약한 암호면 가입 실패")
	@Test
	public void if_weak_password_then_join_fail() throws Exception {
		stubWeakPasswordChecker.setWeak(true); // 암호가 약하다고 응답하도록 설정

		assertThrows(WeakPasswordException.class, () -> {
			userRegister.register("id", "pw", "email");
		});
	}

	@DisplayName("2. 이미 같은 ID가 존재하면 가입 실패")
	@Test
	public void if_duplicateId_then_fail() throws Exception {
		fakeRepository.save(new User("id", "pw", "email@email.com"));

		assertThrows(DupidExeption.class, () -> {
			userRegister.register("id", "pw2", "email");
		});
	}

	@DisplayName("3. 가입하면 메일을 전송함")
	@Test
	public void register_then_sendEmail() throws Exception {
		userRegister.register("id", "pw", "email@email.com");

		assertTrue(spyEmailNotifier.isCalled());
		assertEquals("email@email.com", spyEmailNotifier.getEmail());
	}
}
