import com.google.gson.Gson;
import com.qiniu.common.QiniuException;
import com.qiniu.http.Response;
import com.qiniu.storage.UploadManager;
import com.qiniu.util.Auth;

import java.awt.*;
import java.awt.datatransfer.StringSelection;
import java.io.File;

public class Main {

	/**
	 * 
	 * @param args
	 *            bucket access_key secret_key domain
	 * @throws Exception
	 */
	public static void main(String[] args) throws Exception {

//        args = new String[]{
//                "pingqc",
//                "a764640F1yw6F5Oy9dpIP3hxcx_WpTe4H3piYRU-",
//                "L-iimF8SuI3Tc_RF6EpwMWb0uMhQy4TsXvr1bT_D",
//                "http://7nj3c7.com1.z0.glb.clouddn.com"
//        };

		//System.out.println(Arrays.toString(args));
		Auth auth = Auth.create(args[1], args[2]);

		UploadManager uploadManager = new UploadManager();
		String imagePath = SaveImage.saveImageFromClipboard();
		File file = new File(imagePath);
		String token = auth.uploadToken(args[0]);
		String extention = imagePath.substring(imagePath.lastIndexOf('.'));
		if (extention == null || extention.length() == 0) {
			extention = "";
		}
		String key = System.currentTimeMillis() + extention;

		try {
			Response res = uploadManager.put(file, key, token);
			System.out.println(res.bodyString());
			if (res.isOK()) {
				UploadResp uploadResp = new Gson().fromJson(res.bodyString(), UploadResp.class);
				String url = args[3] + "/" + uploadResp.key;
				Toolkit.getDefaultToolkit().getSystemClipboard().setContents(new StringSelection(url), null);
				System.out.println("plz use ctrl + v to paste the url:\n" + url);
				System.out.println("================SUCCESS================");
			}
		} catch (QiniuException e) {
			Response r = e.response;
			try {
				// 响应的文本信息
				System.out.println(r.bodyString());
			} catch (QiniuException e1) {
				// ignore
			}
		}
	}

	class UploadResp {
		private String hash;
		private String key;

		public String getHash() {
			return hash;
		}

		public void setHash(String hash) {
			this.hash = hash;
		}

		public String getKey() {
			return key;
		}

		public void setKey(String key) {
			this.key = key;
		}

	}
}
