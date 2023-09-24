package api.endpoints;

/**
 * This class consists of all the request urls
 * 
 * @author sharu
 *
 */
public class Routes {

	public static String base_url = "https://petstore.swagger.io/v2";

	/**
	 * User model urls
	 *
	 */

	public static String get_user_url = "https://petstore.swagger.io/v2/user/{username}";
	public static String post_user_url = "https://petstore.swagger.io/v2/user";
	public static String delete_user_url = "https://petstore.swagger.io/v2/user/{username}";
	public static String update_user_url = "https://petstore.swagger.io/v2/user/{username}";
	public static String post_UserArray_url = "https://petstore.swagger.io/v2/user/createWithArray";

}
