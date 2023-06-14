package com.app.pos2.config;

import com.app.pos2.entity.RoleEntity;
import com.app.pos2.entity.UserEntity;
import com.app.pos2.service.RoleService;
import com.app.pos2.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Arrays;

@Service
public class DbInit implements CommandLineRunner {
    private PasswordEncoder encoder;
    private UserService userService;
    private RoleService roleService;

    @Autowired
    public DbInit(PasswordEncoder encoder, UserService userService, RoleService roleService) {
        this.encoder = encoder;
        this.userService = userService;
        this.roleService = roleService;
    }
    private void initUserAndRole(){
        if(roleService.getCount() == 0){
            this.roleService.save(Arrays.asList(
                    new RoleEntity("ROLE_ADMIN"),
                    new RoleEntity("ROLE_USER"),
                    new RoleEntity("ROLE_DOSEN"),
                    new RoleEntity("ROLE_MAHASISWA"),
                    new RoleEntity("ROLE_KEUANGAN")
            ));
        }
        if(userService.getCount()==0){
            RoleEntity adminRole = roleService.getByName("ROLE_ADMIN");
            UserEntity admin = new UserEntity("admin", encoder.encode("admin32!"),"admin@gmail.com", Arrays.asList(adminRole));
            this.userService.save(admin);

            RoleEntity mhsRole = roleService.getByName("ROLE_MAHASISWA");
            UserEntity mhs = new UserEntity("mahasiswa", encoder.encode("mahasiswa32!"),"mahasiswa@gmail.com", Arrays.asList(mhsRole));
            this.userService.save(mhs);

            RoleEntity dosenRole = roleService.getByName("ROLE_DOSEN");
            UserEntity dosen = new UserEntity("dosen", encoder.encode("dosen32!"),"dosen@gmail.com", Arrays.asList(dosenRole));
            this.userService.save(dosen);
        }
    }
    @Override
    public void run(String... args) throws Exception {
        initUserAndRole();
    }
}
