package dk.mf.rpc.bank.server;

import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;

import javax.persistence.Entity;
import javax.persistence.Id;
import java.io.Serializable;

@Data
@NoArgsConstructor
@RequiredArgsConstructor
@Entity
public class Customer implements Serializable
{
    @Id
    private Long accnum;
    @NonNull
    private String name;
    @NonNull
    private Double amount;
}
