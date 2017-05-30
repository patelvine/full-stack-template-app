package xero;

import com.google.api.client.auth.oauth.OAuthHmacSigner;
import com.google.api.client.auth.oauth.OAuthRsaSigner;

public interface Signer {
	public OAuthRsaSigner createRsaSigner();
	public OAuthHmacSigner createHmacSigner();
}
