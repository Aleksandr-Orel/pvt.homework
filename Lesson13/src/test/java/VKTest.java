import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.utils.URIBuilder;
import org.apache.http.impl.client.HttpClientBuilder;
import org.apache.http.util.EntityUtils;
import org.junit.Assert;
import java.io.IOException;
import java.net.URISyntaxException;

public class VKTest {
    public static void main(String[] args) throws IOException, URISyntaxException {
        final String ACCESS_TOKEN = "730c3c1c50456f57ca27dd981b4357ea5882a9cf92f3488dc73cd1153604cdd17f1b4800277be16bb3ff6";
        final String OWNER_ID = "143006819";

        HttpClient client = HttpClientBuilder.create().build();
        URIBuilder builder1 = new URIBuilder("https://api.vk.com/method/wall.post?");
        builder1.setParameter("access_token", ACCESS_TOKEN)
                .setParameter("owner_id", OWNER_ID)
                .setParameter("message", "...")
                .setParameter("v", "5.103");
        HttpGet request = new HttpGet(builder1.build());
        HttpResponse response = client.execute(request);
        String result = EntityUtils.toString(response.getEntity());
        System.out.println(result);
        String postId = result.replaceAll("\\D", "");
        System.out.println(postId);
        Assert.assertTrue(postId.matches("\\d+"));

        URIBuilder builder2 = new URIBuilder("https://api.vk.com/method/wall.edit?");
        builder2.setParameter("access_token", ACCESS_TOKEN)
                .setParameter("owner_id", OWNER_ID)
                .setParameter("post_id", postId)
                .setParameter("message", "///")
                .setParameter("v", "5.103");
        request = new HttpGet(builder2.build());
        response = client.execute(request);
        result = EntityUtils.toString(response.getEntity());
        System.out.println(result);
        String postIdAfterEdit = result.replaceAll("\\D", "");
        System.out.println(postIdAfterEdit);
        Assert.assertEquals(postIdAfterEdit, postId);

        String expectedResponse = "1";
        URIBuilder builder3 = new URIBuilder("https://api.vk.com/method/wall.delete?");
        builder3.setParameter("access_token", ACCESS_TOKEN)
                .setParameter("owner_id", OWNER_ID)
                .setParameter("post_id", postId)
                .setParameter("v", "5.103");
        request = new HttpGet(builder3.build());
        response = client.execute(request);
        result = EntityUtils.toString(response.getEntity());
        System.out.println(result);
        postId = result.replaceAll("\\D", "");
        Assert.assertEquals(postId, expectedResponse);
    }
}
