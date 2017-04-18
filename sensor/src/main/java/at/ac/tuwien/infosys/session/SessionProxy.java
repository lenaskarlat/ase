package at.ac.tuwien.infosys.session;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.context.annotation.ScopedProxyMode;
import org.springframework.stereotype.Service;

/**
 * Created by lenaskarlat on 4/18/17.
 */
public interface SessionProxy {
    ReceivedDataTimeStampsSessionBean getReceivedDataTimeStampsSessionBean();
}

@SuppressWarnings("SpringAutowiredFieldsWarningInspection")
@Service
@Scope(scopeName = "session", proxyMode = ScopedProxyMode.INTERFACES)
class SessionProxyImpl implements SessionProxy {
    @Autowired
    private ReceivedDataTimeStampsSessionBean receivedDataTimeStampsSessionBean;

    @Override
    public ReceivedDataTimeStampsSessionBean getReceivedDataTimeStampsSessionBean() {
        return receivedDataTimeStampsSessionBean;
    }
}
