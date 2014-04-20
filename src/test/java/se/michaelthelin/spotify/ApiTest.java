package se.michaelthelin.spotify;

import org.junit.Test;
import se.michaelthelin.spotify.methods.Request;
import se.michaelthelin.spotify.UtilProtos.Url.Scheme;
import se.michaelthelin.spotify.UtilProtos.Url.Parameter;
import se.michaelthelin.spotify.UtilProtos.Url;
import static se.michaelthelin.spotify.SpotifyProtos.AlbumType.ALBUM;
import static se.michaelthelin.spotify.SpotifyProtos.AlbumType.SINGLE;

import static junit.framework.TestCase.assertEquals;
import static org.junit.Assert.fail;

/**
 * Unit tests for the API class.
 */
public class ApiTest {

  @Test
  public void shouldCreateAGetAlbumUrl() {
    Api api = Api.DEFAULT_API;
    Request request = api.album().id("5oEljuMoe9MXH6tBIPbd5e").build();
    assertEquals("https://api.spotify.com:80/v1/albums/5oEljuMoe9MXH6tBIPbd5e", request.toString());
  }

  @Test
  public void shouldCreateAGetArtistUrl() {
    Api api = Api.DEFAULT_API;
    Request request = api.artist().id("5rSXSAkZ67PYJSvpUpkOr7").build();
    assertEquals("https://api.spotify.com:80/v1/artists/5rSXSAkZ67PYJSvpUpkOr7", request.toString());
  }

  @Test
  public void shouldCreateAGetTrackUrl() {
    Api api = Api.DEFAULT_API;
    Request request = api.track().id("6hDH3YWFdcUNQjubYztIsG").build();
    assertEquals("https://api.spotify.com:80/v1/tracks/6hDH3YWFdcUNQjubYztIsG", request.toString());
  }

  @Test
  public void shouldCreateAGetAlbumsUrl() {
    Api api = Api.DEFAULT_API;
    Request request = api.album().id("6hDH3YWFdcUNQjubYztIsG", "2IA4WEsWAYpV9eKkwR2UYv").build();
    assertEquals("https://api.spotify.com:80/v1/albums", request.toString());
    assertHasParameter(request.toUrl(), "ids", "6hDH3YWFdcUNQjubYztIsG,2IA4WEsWAYpV9eKkwR2UYv");
  }

  @Test
  public void shouldCreateAGetTracksUrl() {
    Api api = Api.DEFAULT_API;
    Request request = api.track().id("6hDH3YWFdcUNQjubYztIsG","2IA4WEsWAYpV9eKkwR2UYv").build();
    assertEquals("https://api.spotify.com:80/v1/tracks", request.toString());
    assertHasParameter(request.toUrl(), "ids", "6hDH3YWFdcUNQjubYztIsG,2IA4WEsWAYpV9eKkwR2UYv");
  }

  @Test
  public void shouldCreateAUrlForArtistsAlbum() {
    Api api = Api.DEFAULT_API;
    Request request = api.album().forArtist("4AK6F7OLvEQ5QYCBNiQWHq").build();
    assertEquals("https://api.spotify.com:80/v1/artists/4AK6F7OLvEQ5QYCBNiQWHq/albums", request.toString());
  }

  @Test
  public void shouldHaveAlbumTypeParametersInArtistsAlbumUrl() {
    Api api = Api.DEFAULT_API;
    Request request = api.album().forArtist("4AK6F7OLvEQ5QYCBNiQWHq").types(ALBUM, SINGLE).build();
    assertEquals("https://api.spotify.com:80/v1/artists/4AK6F7OLvEQ5QYCBNiQWHq/albums", request.toString());
    assertHasParameter(request.toUrl(), "album_type", "ALBUM,SINGLE");
  }

  @Test
  public void shouldHaveLimitParameterInArtistsAlbumUrl() {
    Api api = Api.DEFAULT_API;
    Request request = api.album().forArtist("4AK6F7OLvEQ5QYCBNiQWHq").limit(2).build();
    assertEquals("https://api.spotify.com:80/v1/artists/4AK6F7OLvEQ5QYCBNiQWHq/albums", request.toString());
    assertHasParameter(request.toUrl(), "limit", "2");
  }

  @Test
  public void shouldHaveOffsetParameterInArtistsAlbumUrl() {
    Api api = Api.DEFAULT_API;
    Request request = api.album().forArtist("4AK6F7OLvEQ5QYCBNiQWHq").offset(5).build();
    assertEquals("https://api.spotify.com:80/v1/artists/4AK6F7OLvEQ5QYCBNiQWHq/albums", request.toString());
    assertHasParameter(request.toUrl(), "offset", "5");
  }

  @Test
  public void shouldHaveSeveralQueryParametersAtTheSameTimeInArtistsAlbumUrl() {
    Api api = Api.DEFAULT_API;
    Request request = api.album().forArtist("4AK6F7OLvEQ5QYCBNiQWHq").types(SINGLE).limit(2).offset(5).build();
    assertEquals("https://api.spotify.com:80/v1/artists/4AK6F7OLvEQ5QYCBNiQWHq/albums", request.toString());
    assertHasParameter(request.toUrl(), "offset", "5");
    assertHasParameter(request.toUrl(), "limit", "2");
    assertHasParameter(request.toUrl(), "album_type", "SINGLE");
  }

  @Test
  public void shouldCreateAGetArtistsUrl() {
    Api api = Api.DEFAULT_API;
    Request request = api.artist().id("4AK6F7OLvEQ5QYCBNiQWHq","6rEzedK7cKWjeQWdAYvWVG").build();
    assertEquals("https://api.spotify.com:80/v1/artists", request.toString());
    assertHasParameter(request.toUrl(), "ids", "4AK6F7OLvEQ5QYCBNiQWHq,6rEzedK7cKWjeQWdAYvWVG");
  }

  @Test
  public void shouldCreateSearchUrl() {
    Api api = Api.DEFAULT_API;
    Request request = api.search().query("moulat swalf").build();
    assertEquals("https://api.spotify.com:80/v1/search", request.toString());
    assertHasParameter(request.toUrl(), "q", "moulat+swalf");
  }

  @Test
  public void shouldCreateSearchUrlWithLimitParameter() {
    Api api = Api.DEFAULT_API;
    Request request = api.search().query("moulat swalf").limit(2).build();
    assertEquals("https://api.spotify.com:80/v1/search", request.toString());
    assertHasParameter(request.toUrl(), "q", "moulat+swalf");
    assertHasParameter(request.toUrl(), "limit", "2");
  }

  @Test
  public void shouldCreateSearchUrlWithOffsetParameter() {
    Api api = Api.DEFAULT_API;
    Request request = api.search().query("moulat swalf").offset(2).build();
    assertEquals("https://api.spotify.com:80/v1/search", request.toString());
    assertHasParameter(request.toUrl(), "q", "moulat+swalf");
    assertHasParameter(request.toUrl(), "offset", "2");
  }

  @Test
  public void shouldCreateSearchUrlWithTypeParameter() {
    Api api = Api.DEFAULT_API;
    Request request = api.search().query("moulat swalf").type("album").build();
    assertEquals("https://api.spotify.com:80/v1/search", request.toString());
    assertHasParameter(request.toUrl(), "q", "moulat+swalf");
    assertHasParameter(request.toUrl(), "type", "album");
  }

  @Test
  public void shouldModifySchemeInUrl() {
    Api api = Api.builder().scheme(Scheme.HTTP).build();
    Request request = api.album().id("5oEljuMoe9MXH6tBIPbd5e").build();
    assertEquals("http://api.spotify.com:80/v1/albums/5oEljuMoe9MXH6tBIPbd5e", request.toString());
  }

  @Test
  public void shouldModifyPortInUrl() {
    Api api = Api.builder().port(8080).build();
    Request request = api.album().id("5oEljuMoe9MXH6tBIPbd5e").build();
    assertEquals("https://api.spotify.com:8080/v1/albums/5oEljuMoe9MXH6tBIPbd5e", request.toString());
  }

  @Test
  public void shouldModifyHostInUrl() {
    Api api = Api.builder().host("www.michaelthelin.se").build();
    Request request = api.album().id("5oEljuMoe9MXH6tBIPbd5e").build();
    assertEquals("https://www.michaelthelin.se:80/v1/albums/5oEljuMoe9MXH6tBIPbd5e", request.toString());
  }

  void assertHasParameter(Url url, String name, Object value) {
    Parameter expected = Parameter.newBuilder().setName(name).setValue(value.toString()).build();
    for (Parameter actual : url.getParametersList()) {
      if (actual.equals(expected)) {
        return;
      }
    }
    fail(String.format("Actual URL %s does not contain parameter %s", url, expected));
  }

}