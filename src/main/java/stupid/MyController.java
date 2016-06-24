package stupid;

import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;

import java.io.IOException;

@Controller
@RequestMapping("/")
public class MyController {


    @RequestMapping("/")
    public String onIndex() {
        return "index";
    }

    @RequestMapping("/allpics")
    public ModelAndView onAllPics() {
        DeleteOrZip deleteOrZip = new DeleteOrZip(Data.getPhotos().keySet());
        ModelAndView modelAndView = new ModelAndView("allpics", "deletezip", deleteOrZip);
        return modelAndView;
    }

    @RequestMapping(value = "/allpics", params = "zip")
    public Object zipPhotos(@ModelAttribute("deletezip") DeleteOrZip deleteOrZip) {
        if ( (deleteOrZip.getPhotosDelete() != null) && (!deleteOrZip.getPhotosDelete().isEmpty()) ) {
            byte[] mediaBody = ZipService.zipFiles(Data.getPhotos(), deleteOrZip.getPhotosDelete());
            HttpHeaders httpHeaders = new HttpHeaders();
            httpHeaders.setContentType(new MediaType("all", "zip"));
            httpHeaders.set("Content-Disposition", "attachment; filename=archive.zip");
            httpHeaders.setContentLength(mediaBody.length);
            return new ResponseEntity<byte[]>(mediaBody, httpHeaders, HttpStatus.OK);
        }
        return "redirect:allpics";
    }

    @RequestMapping(value = "/allpics", params = "delete")
    public String deletePhotos (@ModelAttribute("deletezip") DeleteOrZip deleteOrZip) {
        if (deleteOrZip.getPhotosDelete() != null) {
            for (Long number : deleteOrZip.getPhotosDelete()) {
                Data.getPhotos().remove(number);
            }
        }
        return "redirect:allpics";
    }

    @RequestMapping(value = "/add_photo", method = RequestMethod.POST)
    public String onAddPhoto(Model model, @RequestParam MultipartFile photo) {
        if (photo.isEmpty())
            throw new PhotoErrorException();

        try {
            long id = System.currentTimeMillis();
            Data.getPhotos().put(id, photo.getBytes());

            model.addAttribute("photo_id", id);
            return "result";
        } catch (IOException e) {
            throw new PhotoErrorException();
        }
    }

    @RequestMapping("/photo/{photo_id}")
    public ResponseEntity<byte[]> onPhoto(@PathVariable("photo_id") long id) {
        return photoById(id);
    }

    @RequestMapping(value = "/view", method = RequestMethod.POST)
    public ResponseEntity<byte[]> onView(@RequestParam("photo_id") long id) {
        return photoById(id);
    }

    @RequestMapping("/delete/{photo_id}")
    public String onDelete(@PathVariable("photo_id") long id) {
        if (Data.getPhotos().remove(id) == null)
            throw new PhotoNotFoundException();
        else
            return "index";
    }

    private ResponseEntity<byte[]> photoById(long id) {
        byte[] bytes = Data.getPhotos().get(id);
        if (bytes == null)
            throw new PhotoNotFoundException();

        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.IMAGE_JPEG);

        return new ResponseEntity<byte[]>(bytes, headers, HttpStatus.OK);
    }
}
