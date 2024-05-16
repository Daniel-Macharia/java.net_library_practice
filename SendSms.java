
import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URI;
import java.net.URISyntaxException;
import java.net.URL;

public class SendSms
{
	public static void main( String[] args)
	{
		try
		{
			String id = "\"senderID\"";
			String idVal = "\"MOBILESASA\"";
			String m = "\"message\"";
			String mVal = "\"Dear visitor, your number is 44.\"";
			String p = "\"phone\"";
			String pVal = "\"254712696965\"";
			String pVal2 = "\"254713798133\"";
			String pVal3 = "\"254703763448\"";
			
			String url = "https://api.mobilesasa.com/v1/send/message";
			String data = "{" 
							+ id + ": " + idVal + "," 
							+ m + ": " + mVal + "," 
							+ p + ": " + pVal + "}";
							
			String data2 = "{" 
							+ id + ": " + idVal + "," 
							+ m + ": " + mVal + "," 
							+ p + ": " + pVal2 + "}";
							
			String data3 = "{" 
							+ id + ": " + idVal + "," 
							+ m + ": " + mVal + "," 
							+ p + ": " + pVal3 + "}";
							
			
			for( int i = 0; i < 3; i++ )
			{
				URL object = new URI(url).toURL();
			
				HttpURLConnection connection = (HttpURLConnection) object.openConnection();
				
				connection.setRequestMethod("POST");
				connection.setDoOutput(true);
				connection.setRequestProperty("Authorization", "Bearer myApiKey");
				connection.setRequestProperty("Accept", "application/json");
				connection.setRequestProperty("Content-Type", "application/json");
				
				DataOutputStream os = new DataOutputStream(connection.getOutputStream());
				if( i == 0)
					os.writeBytes( data );
				else if( i == 1 )
					os.writeBytes( data2 );
				else
					os.writeBytes( data3 );
				os.flush();
				
				int responseCode = connection.getResponseCode();
				
				if( responseCode == HttpURLConnection.HTTP_OK )
				{
					StringBuilder response = new StringBuilder();
					BufferedReader reader = new BufferedReader(new InputStreamReader(connection.getInputStream()));
					String line;
					
					while( (line = reader.readLine() ) != null )
					{
						response.append(line);
					}
					
					System.out.println("Response: " + response.toString());
				}
				else
				{
					System.out.println("No reponse received.");
					connection.disconnect();
				}
			
			}
		
		}catch( Exception e )
		{
			System.out.println("Error: " + e);
		}
	}
}