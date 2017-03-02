package woodstore.service;

/**
 * Created by Viktor_Artemov on 3/2/2017.
 */
public interface SecurityService {
    String findLoggedInUsername();

    void autoLogin(String username, String password);
}
