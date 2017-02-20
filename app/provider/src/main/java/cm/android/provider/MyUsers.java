package cm.android.provider;

import android.net.Uri;
import android.provider.BaseColumns;

/**
 * 定义了Content Provider的CONTENT_URI，以及数据列
 * @author miaowei
 *
 */
public class MyUsers {

	/**
	 * Content Provider的CONTENT_URI
	 */
	public static final String AUTHORITY = "com.example.sivan.MyContentProvider";
	/**
	 * 数据列
	 * @author miaowei
	 *
	 */
	public static final class User implements BaseColumns{
		
		public static final Uri CONTENT_URL = Uri.parse("content://"+AUTHORITY);
		
		//表数据列
		public static final String USER_NAME = "USER_NAME";
		
	}
	
}
