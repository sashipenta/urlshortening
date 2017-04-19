// Author : Sashi Penta

package shortening;

public class URLObject
{
    private final String code;
    private final String url;

    public URLObject(String code, String url)
    {
        this.code = code;
        this.url = url;
    }

    public String getCode()
    {
        return code;
    }

    public String getUrl()
    {
        return url;
    }
}
