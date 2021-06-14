package br.com.navita.commons.utils;

import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;
import java.security.spec.InvalidKeySpecException;
import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

import org.wildfly.security.password.Password;
import org.wildfly.security.password.PasswordFactory;
import org.wildfly.security.password.WildFlyElytronPasswordProvider;
import org.wildfly.security.password.interfaces.BCryptPassword;
import org.wildfly.security.password.util.ModularCrypt;

import br.com.navita.commons.excecoes.ExcecaoGeral;
import br.com.navita.usuario.model.Usuario;
import io.quarkus.elytron.security.common.BcryptUtil;
import io.smallrye.jwt.build.Jwt;

public abstract class TokenUtils {
    
	//private static final String secret = "AyM1SysPpbyDfgZld3umj1qzKObwVMko";

    public static String gerarToken(Usuario usu) {
        if(usu == null || usu.getEmail() == null) {
            return null;
        }
        Set<String>papeis = new HashSet<>();
        if(usu.getPapeis() != null && !usu.getPapeis().isBlank()) {
            papeis.addAll( Arrays.asList(usu.getPapeis().split(",")) );
        }
        String token =
           Jwt.issuer("https://navita.com.br/") 
             .upn(usu.getEmail()) 
             .groups(papeis)
			 //.signWithSecret(secret);
             .sign();
        return token;
    }

    public static boolean verifyBCryptPassword(String bCryptPasswordHash, String passwordToVerify) throws ExcecaoGeral {

        WildFlyElytronPasswordProvider provider = new WildFlyElytronPasswordProvider();

        try {
            // 1. Create a BCrypt Password Factory
            PasswordFactory passwordFactory = PasswordFactory.getInstance(BCryptPassword.ALGORITHM_BCRYPT, provider);

			// 2. Decode the hashed user password
			Password userPasswordDecoded = ModularCrypt.decode(bCryptPasswordHash);
			
			// 3. Translate the decoded user password object to one which is consumable by this factory.
			Password userPasswordRestored = passwordFactory.translate(userPasswordDecoded);

			// Verify existing user password you want to verify
			return passwordFactory.verify(userPasswordRestored, passwordToVerify.toCharArray());

        } catch (NoSuchAlgorithmException | InvalidKeySpecException | InvalidKeyException e) {
            throw new ExcecaoGeral("Erro durante a verificação de senha: "+e.getMessage(), e);
        }

    }

    public static String hash(String value) {
        return BcryptUtil.bcryptHash(value, 11);
    }
}
