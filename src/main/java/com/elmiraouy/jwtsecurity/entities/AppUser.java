package com.elmiraouy.jwtsecurity.entities;

import jakarta.persistence.*;
import lombok.*;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;
import java.util.List;
@Entity
@Builder
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public  class AppUser implements UserDetails {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id ;

    private String firstName;
    private String lastName;
    private String password;
    private String email;
    private String telephone;
    private String address;
    private String uuid;
    private String ville;
    private Date uuidExpiredDate;
    private String status ;
    private String matricule;
    private String urlImage;
    private LocalDateTime dateCreation;
    private LocalDateTime dateUpdate;
    private LocalDateTime dateBlocked;
    @ManyToMany(fetch = FetchType.EAGER)
    @JoinTable(
            name = "appuser_approle",
            joinColumns = @JoinColumn(name = "appuser_id"),
            inverseJoinColumns = @JoinColumn(name = "approle_id")
    )
    private Collection<AppRole> appRoles = new ArrayList<>();

    @OneToOne( cascade = {})
    private Token token=new Token();
    @ManyToMany(mappedBy = "users",fetch = FetchType.EAGER)
    private Collection<Company> companies=new ArrayList<>();

    // si user est chef alors ces utilis sont sous ca responsabilite
    @OneToMany(fetch = FetchType.EAGER)
    private List<AppUser> subordinateUsers;

    // son chef
    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "chef_id")
    private AppUser chef;

    @OneToMany(mappedBy = "manager",fetch = FetchType.EAGER)
    private List<UnitOrganisational> unitsWhereIsChef;
    @OneToOne
    private Image image;

    @OneToMany(mappedBy = "admin",fetch = FetchType.EAGER)
    private Collection<Company> adminOnCompanies;
    @Override
    public String getPassword() {
        return password;
    }

    @Override
    public String getUsername() {
        return email;
    }

    @Transactional
    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        List<SimpleGrantedAuthority> authorities = new ArrayList<>();
        for (AppRole appRole : appRoles) {
            authorities.add(new SimpleGrantedAuthority(appRole.getRoleName()));
        }
        return authorities;
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return true;
    }
    public AppUser(Long id, String firstName, String lastName, String passWord, String email,
                   String telephone, String address, String uuid, String ville) {
        this.id=id;
        this.firstName=firstName;
        this.lastName=lastName;
        this.password=passWord;
        this.email=email;
        this.telephone=telephone;
        this.address=address;
        this.ville=ville;
        this.uuid=uuid;
    }
}
