package org.sitmun.plugin.core.web.rest;

import io.swagger.v3.oas.annotations.tags.Tag;
import java.util.Optional;
import javax.validation.Valid;
import org.sitmun.plugin.core.domain.User;
import org.sitmun.plugin.core.repository.UserRepository;
import org.sitmun.plugin.core.security.SecurityUtils;
import org.sitmun.plugin.core.service.UserService;
import org.sitmun.plugin.core.service.dto.UserDTO;
import org.sitmun.plugin.core.web.rest.dto.PasswordDTO;
import org.springframework.data.rest.webmvc.RepositoryRestController;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@RepositoryRestController
@RequestMapping("/api/account")
@Tag(name = "account", description = "user account management")
public class AccountResource {

  private final UserService userService;

  private final UserRepository userRepository;

  //@Autowired
  //private RepositoryEntityLinks links;

  /**
   * Constructor.
   *
   * @param userService    user service
   * @param userRepository user repository
   */
  public AccountResource(UserService userService, UserRepository userRepository) {
    super();
    this.userService = userService;
    this.userRepository = userRepository;
  }

  /**
   * Update en existing account.
   *
   * @param userDTO account to be updated
   * @return ok if the account has been updated
   */
  @PostMapping("")
  @ResponseBody
  public ResponseEntity<Void> saveAccount(@Valid @RequestBody UserDTO userDTO) {
    Optional<String> optLogin = SecurityUtils.getCurrentUserLogin();
    if (optLogin.isPresent()) {
      Optional<User> user = userRepository.findOneByUsername(optLogin.get());
      if (user.isPresent()) {
        userService.updateUser(user.get().getId(), userDTO.getFirstName(), userDTO.getLastName());
        return ResponseEntity.ok().build();
      } else {
        return ResponseEntity.notFound().build();
      }
    } else {
      return ResponseEntity.notFound().build();
    }

  }

  /**
   * Get accounts.
   */
  @GetMapping("")
  @ResponseBody
  public ResponseEntity<User> getAccount() {
    Optional<String> optLogin = SecurityUtils.getCurrentUserLogin();
    if (optLogin.isPresent()) {
      Optional<User> user = userRepository.findOneWithPermissionsByUsername(optLogin.get());
      //            toResource(user.get()));
      return user.map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.notFound().build());

    } else {
      return ResponseEntity.notFound().build();
    }
  }

  //  private ResourceSupport toResource(User user) {
  //    UserDTO dto = new UserDTO(user);
  //    Link selfLink = links.linkForSingleResource(user).withSelfRel();
  //
  //    return new Resource<>(dto);
  //  }

  /**
   * Change account password.
   *
   * @param password new password
   * @return ok if the password has been updated
   */
  @PostMapping(path = "/change-password")
  @ResponseBody
  public ResponseEntity<Void> changePassword(@RequestBody PasswordDTO password) {
    Optional<String> optLogin = SecurityUtils.getCurrentUserLogin();
    if (optLogin.isPresent()) {
      Optional<User> user = userRepository.findOneByUsername(optLogin.get());
      if (user.isPresent()) {
        userService.changeUserPassword(user.get().getId(), password.getPassword());
        return ResponseEntity.ok().build();
      } else {
        return ResponseEntity.notFound().build();
      }

    } else {
      return ResponseEntity.notFound().build();
    }


  }

}
