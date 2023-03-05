package com.lcwd.electronicsStrore.electronicsStore.Controllers;

import com.lcwd.electronicsStrore.electronicsStore.DTO.ApiResponseMessege;
import com.lcwd.electronicsStrore.electronicsStore.DTO.ImageResponse;
import com.lcwd.electronicsStrore.electronicsStore.DTO.UserDto;
import com.lcwd.electronicsStrore.electronicsStore.Services.FileSevice;
import com.lcwd.electronicsStrore.electronicsStore.Services.UserService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.util.StreamUtils;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;
import java.io.IOException;
import java.io.InputStream;
import java.util.List;

@RestController
@RequestMapping("/users")
public class UserController {
    @Autowired
    private UserService userService;
    @Autowired
    private FileSevice fileSevice;

    private Logger logger= LoggerFactory.getLogger(UserController.class);
    @Value("${user.profile.image.path}")
    private String imageUploadpath;
    @PostMapping
    public ResponseEntity<UserDto> createUser(@Valid @RequestBody UserDto userDto) {
        UserDto userDto1 = userService.createUser(userDto);
        return new ResponseEntity<>(userDto1, HttpStatus.CREATED);
    }

    @PutMapping("/{userId}")
    public ResponseEntity<UserDto> updateUser(@PathVariable("userId") String userId, @Valid @RequestBody UserDto userDto) {
        UserDto updatedUserDto = userService.updateUser(userDto, userId);
        return new ResponseEntity<>(updatedUserDto,HttpStatus.OK);
    }

    @DeleteMapping("/{userId}")
    public ResponseEntity<ApiResponseMessege> deleteUser(@PathVariable String userId) {

        userService.deleteUser(userId);
        ApiResponseMessege messege = ApiResponseMessege.builder().
                messege("user is deleted successfully").
                success(true).
                status(HttpStatus.OK).
                build();
        return new ResponseEntity<>(messege,HttpStatus.OK);

    }

    @GetMapping
    public ResponseEntity<List<UserDto>> getAllUser() {
        return new ResponseEntity<>(userService.getAllUser(),HttpStatus.OK);

    }

    @GetMapping("/{userId}")
    public ResponseEntity<UserDto> getUser(@PathVariable String userId) {
        return new ResponseEntity<>(userService.getUserById(userId),HttpStatus.OK);

    }

    @GetMapping("/email/{email}")
    public ResponseEntity<UserDto> getUserByEmail(@PathVariable String email) {
        return new ResponseEntity<>(userService.getUserByEmail(email),HttpStatus.OK);
    }

    @GetMapping("/search/{keyword}")
    public ResponseEntity<List<UserDto>> searchUser(@PathVariable String keyword) {
        return new ResponseEntity<>(userService.searchUser(keyword),HttpStatus.OK);

    }

    //upload user image
    @PostMapping("/image/{userId}")
    public ResponseEntity<ImageResponse> uploadImage(@RequestParam("UserImage")MultipartFile image,@PathVariable String userId) throws IOException {
        String imageName = fileSevice.uploadFile(image,imageUploadpath);
        UserDto user = userService.getUserById(userId);
        user.setImageName(imageName);
        UserDto userDto = userService.updateUser(user, userId);
        ImageResponse imageResponse=ImageResponse.builder().imageName(imageName).success(true).status(HttpStatus.CREATED).build();
        return new ResponseEntity<>(imageResponse,HttpStatus.CREATED);
    }


    // serve image
    @GetMapping("/image/{userId}")
    public void serveImage(@PathVariable String userId, HttpServletResponse response) throws IOException {
        UserDto user=userService.getUserById(userId);
        logger.info("User image name:{}",user.getImageName());
        InputStream resource = fileSevice.getResource(imageUploadpath, user.getImageName());

        response.setContentType(MediaType.IMAGE_JPEG_VALUE); //MediaType.IMAGE_JPEG_VALUE to indicate that the image being served is in JPEG format.

        StreamUtils.copy(resource,response.getOutputStream());
    }

}
