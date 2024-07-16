package me.korolz.encryptfactory.models;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.redis.core.RedisHash;

@Data
@AllArgsConstructor
@NoArgsConstructor
@RedisHash("CipherModel")
public class CipherModel {
    @Id
    private String publicKey;
    private String privateKey;
}
