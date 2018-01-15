package kevin.com.passwords;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.Toast;

import static android.R.attr.data;

public class LoginActivity extends AppCompatActivity {

    public static int APP_REQUEST_CODE = 1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        FontHelper.setCustomTypeface(findViewById(R.id.view_root));

//        Check for an existing access token
        AccessToken accessToken = AccountKit.getCurrentAccessToken();
        if (accessToken != null) {
            //if previously logged in, proceed to the account activity
            launchAccountActivity();
        }

    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        //Confirm that this request matches your request
        if (requestCode == APP_REQUEST_CODE) {
            AccountKitLoginResult loginResult = data.getParcelableExtra(AccountKitLoginResult.RESULT_KEY);
            if(loginResult != null ) {
                //display login error
                String toastMessage = loginResult.getError().getErrorType().getMessage;
                Toast.makeText(this, toastMessage, Toast.LENGTH_SHORT).show();
            } else if (loginResult.getAccessToken() != null) {
                //on successful login, procede to the account activity
               launchAccountActivity();
            }

        }
    }


    private void onLogin(final LoginType loginType) {
        //Create account for the account kit activity
        final Intent intent = new Intent(this, AccountKitActivity.class);

        //Configure login type and response type
        AccountKitConfiguration.AccountKitConfigurationBuilder configurationBuilder =
                new AccountKitConfiguration.AccountKitConfigurationBuilder {
            loginType,
                    AccountKitActivity.ResponseType.TOKEN
        } ;
        final AccountKitConfiguration configuration = configurationBuilder.build();

        intent.putExtra(AccountKitActivity.ACCOUNT_KIT_ACTIVITY_CONFIGURATION, configuration);
        startActivityForResult(intent, APP_REQUEST_CODE);
    }

    public void onPhoneLogin() {
        onLogin(LoginType.PHONE);
    }

    public void onEmailLogin() {
        onLogin(LoginType.EMAIL);
    }


    private void launchAccountActivity() {
        Intent intent = new Intent(LoginActivity.this, AccountActivity.class);
        startActivity(intent);
        finish();
    }
}
