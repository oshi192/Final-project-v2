package util;

import org.apache.log4j.Logger;

import javax.servlet.http.HttpServletRequest;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Objects;

public class AccessMapper {
    private final static Logger logger = Logger.getLogger(AccessMapper.class);
    private static HashMap<String, List<String>> rights;
    private static AccessMapper instance;

    private AccessMapper() {
        rights = inializeRights();
    }

    private HashMap<String, List<String>> inializeRights() {

        rights = new HashMap<>();
        rights.put("GUEST",Arrays.asList(
                "",
                "login",
                "registration",
                "/taxi"
        ));
        rights.put("USER",Arrays.asList(
                "",
                "logout",
                "/taxi"
        ));
        rights.put("ADMIN",Arrays.asList(
                "",
                "logout",
                "taxis",
                "/taxi"
        ));
        return rights;
    }

    public static AccessMapper getInstance() {
        if (Objects.isNull(instance)) {
            instance = new AccessMapper();
        }
        return instance;
    }


    public boolean checkRights(HttpServletRequest request, String role) {
        String page = request.getRequestURI().replaceAll(".*/taxi/", "");
        if (page.isEmpty() || page.contains("resources")) {
            return true;
        }
        logger.info("check rights for role : "+role+" with page : "+page +" result : "+rights.get(role).contains(page));
        return rights.get(role).contains(page);
    }
}
