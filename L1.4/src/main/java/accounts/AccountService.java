package accounts;

import dbService.DBException;
import dbService.DBServiceImpl;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by skramble.h
 */
public class AccountService {
    private final Map<String, UserProfile> loginToProfile;
    private final Map<String, UserProfile> sessionIdToProfile;
    DBServiceImpl dbService;

    public AccountService() {
        dbService = new DBServiceImpl();
        loginToProfile = new HashMap<>();
        sessionIdToProfile = new HashMap<>();
    }

    public void addNewUser(UserProfile userProfile) {
        loginToProfile.put(userProfile.getLogin(), userProfile);
        try {
            dbService.addUser(userProfile.getLogin(),userProfile.getPass());
        } catch (DBException e) {
            e.printStackTrace();
        }
    }

    public UserProfile getUserByLogin(String login) {
        //return loginToProfile.get(login);
        try {
            return dbService.getUser(login);
        } catch (DBException e) {
            e.printStackTrace();
        }
        return null;
    }

    public UserProfile getUserBySessionId(String sessionId) {
        return sessionIdToProfile.get(sessionId);
    }

    public void addSession(String sessionId, UserProfile userProfile) {
        sessionIdToProfile.put(sessionId, userProfile);
    }

    public void deleteSession(String sessionId) {
        sessionIdToProfile.remove(sessionId);
    }
}
