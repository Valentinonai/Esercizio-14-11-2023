package Esercizio14112023Progetto.Esercizio14112023Progetto.controllers;

import Esercizio14112023Progetto.Esercizio14112023Progetto.entities.Dispositivo;
import Esercizio14112023Progetto.Esercizio14112023Progetto.entities.User;
import Esercizio14112023Progetto.Esercizio14112023Progetto.entities.UserPayload;
import Esercizio14112023Progetto.Esercizio14112023Progetto.exceptions.BadRequest;
import Esercizio14112023Progetto.Esercizio14112023Progetto.services.DispositivoService;
import Esercizio14112023Progetto.Esercizio14112023Progetto.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;

@RestController
@RequestMapping("/users")
public class UserController {
    @Autowired
    private UserService userService;
    @Autowired
    private DispositivoService dispositivoService;
    @GetMapping()
    @PreAuthorize("hasAuthority('ADMIN')")
    public Page<User> getAllUsers(@RequestParam(defaultValue = "0")int page, @RequestParam(defaultValue = "5")int size, @RequestParam(defaultValue = "id") String order,  Authentication authentication){
        return userService.getAllUsers(page,size>20?5:size,order);
    }
    @GetMapping("/{id}")
    @PreAuthorize("hasAuthority('ADMIN')")
    public User getSingleUser(@PathVariable int id){
        return userService.getSingleUser(id);
    }

    @PutMapping("/{id}")
    @PreAuthorize("hasAuthority('ADMIN')")
    public User modifyUser(@RequestBody @Validated UserPayload userPayload,BindingResult validation,@PathVariable int id){
        if(validation.hasErrors()){
            throw new BadRequest(validation.getAllErrors());
        }
        return userService.modifyUser(userPayload,id);
    }
    @DeleteMapping("/{id}")
    @PreAuthorize("hasAuthority('ADMIN')")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deleteUser(@PathVariable int id)
    {
        userService.deleteUser(id,dispositivoService);
    }


    @PutMapping("/{id}/image")
    @PreAuthorize("hasAuthority('ADMIN')")
    public User modifyImage(@PathVariable int id,@RequestParam("immagineProfilo")MultipartFile file) throws IOException {
        return userService.modifyImage(file,id);
    }


    @GetMapping("/{id}/dispositivi")
    @PreAuthorize("hasAuthority('ADMIN')")
    public List<Dispositivo> getDispositiviById(@PathVariable int id){
        return userService.getDispositiviById(id);
    }


    //----------ENDPOINT /me---------

    @DeleteMapping("/me")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deleteUser(@AuthenticationPrincipal User u)
    {
        userService.deleteUser(u.getId(),dispositivoService);
    }
    @GetMapping("/dispositivi/me")
    public List<Dispositivo> getDispositiviById(@AuthenticationPrincipal User u){
        return userService.getDispositiviById(u.getId());
    }
    @PutMapping("/image/me")
    public User modifyImage(@AuthenticationPrincipal User u,@RequestParam("immagineProfilo")MultipartFile file) throws IOException {
        return userService.modifyImage(file,u.getId());
    }

}
