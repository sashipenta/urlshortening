// Author : Sashi Penta

package shortening;

import java.util.concurrent.atomic.AtomicLong;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class ShorteningController
{
    private static final String code = "defaultCode";

    // TODO: restrict to post
    // TODO: make url mandatory
    @RequestMapping("/shortening")
    public URLObject shortening(@RequestParam(value="url", defaultValue="defaultValue") String name)
    {
        return new URLObject(code, name);
    }
}
