package com.ssgtarbucks.service;

import java.security.SecureRandom;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ssgtarbucks.domain.UserDTO;
import com.ssgtarbucks.persistence.UserRepository;

@Service
public class UserServiceImpl implements UserService {
    private static final String SPECIAL_CHARACTERS = "!@#$%^&*()_-+=<>?";
    private static final String UPPERCASE_LETTERS = "ABCDEFGHIJKLMNOPQRSTUVWXYZ";
    private static final String LOWERCASE_LETTERS = "abcdefghijklmnopqrstuvwxyz";
    private static final String DIGITS = "0123456789";
    
	@Autowired
	private UserRepository userRepository;

    

	@Override
	public UserDTO selectUserByUserId(String user_id) {
		return userRepository.selectUserByUserId(user_id);
	}

	@Override
	public UserDTO selectUserAndBranchToInfo(String user_id) {
		return userRepository.selectUserAndBranchToInfo(user_id);
	}

	@Override
	public int selectCountToFindUserExist(UserDTO userDTO) {
		return userRepository.selectCountToFindUserExist(userDTO);
	}

	@Override
	public int updateUserByUserIdToChgPW(UserDTO userDTO) {
		return userRepository.updateUserByUserIdToChgPW(userDTO);
	}
    
	

	@Override
	public int insertTempCode(String temp_pw_code, String user_id) {
		return userRepository.insertTempCode(temp_pw_code, user_id);
	}


	@Override
	public String selectTempCodeByUserId(String user_id) {
		return userRepository.selectTempCodeByUserId(user_id);
	}
	

	@Override
	public int deleteTempCodeByUserId(String user_id) {
		return userRepository.deleteTempCodeByUserId(user_id);
	}

    
    //임시비밀번호 생성구간
	@Override
	public String generateTempPw() {

        StringBuilder password = new StringBuilder();
        SecureRandom random = new SecureRandom();
        String allCharacters = SPECIAL_CHARACTERS + UPPERCASE_LETTERS + LOWERCASE_LETTERS + DIGITS;

        // Use at least one character from each character set
        password.append(getRandomChar(SPECIAL_CHARACTERS, random));
        password.append(getRandomChar(UPPERCASE_LETTERS, random));
        password.append(getRandomChar(LOWERCASE_LETTERS, random));
        password.append(getRandomChar(DIGITS, random));

        for (int i = 4; i < 10; i++) {
            password.append(getRandomChar(allCharacters, random));
        }

        return shuffleString(password.toString(), random);
	}
	
    private static char getRandomChar(String characterSet, SecureRandom random) {
        int randomIndex = random.nextInt(characterSet.length());
        return characterSet.charAt(randomIndex);
    }
    
    private static String shuffleString(String input, SecureRandom random) {
        char[] characters = input.toCharArray();
        for (int i = characters.length - 1; i > 0; i--) {
            int randomIndex = random.nextInt(i + 1);
            char temp = characters[i];
            characters[i] = characters[randomIndex];
            characters[randomIndex] = temp;
        }
        return new String(characters);
    }


    
}