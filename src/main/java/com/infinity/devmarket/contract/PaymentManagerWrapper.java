package com.infinity.devmarket.contract;

import java.math.BigInteger;
import java.util.Arrays;
import java.util.Collections;

import org.web3j.abi.TypeReference;
import org.web3j.abi.datatypes.Function;
import org.web3j.abi.datatypes.Type;
import org.web3j.crypto.Credentials;
import org.web3j.protocol.Web3j;
import org.web3j.protocol.core.RemoteCall;
import org.web3j.protocol.core.RemoteFunctionCall;
import org.web3j.protocol.core.methods.response.TransactionReceipt;
import org.web3j.tx.Contract;
import org.web3j.tx.TransactionManager;
import org.web3j.tx.gas.ContractGasProvider;

/**
 * <p>Auto generated code.
 * <p><strong>Do not modify!</strong>
 * <p>Please use the <a href="https://docs.web3j.io/command_line.html">web3j command line tools</a>,
 * or the org.web3j.codegen.SolidityFunctionWrapperGenerator in the 
 * <a href="https://github.com/web3j/web3j/tree/master/codegen">codegen module</a> to update.
 *
 * <p>Generated with web3j version 1.5.2.
 */
@SuppressWarnings("rawtypes")
public class PaymentManagerWrapper extends Contract {
    public static final String BINARY = "6080604052348015600e575f80fd5b505f80546001600160a01b0319163317905561013e8061002d5f395ff3fe608060405260043610610028575f3560e01c80631b9265b81461002c57806351cff8d91461002e575b5f80fd5b005b348015610039575f80fd5b5061002c6100483660046100db565b5f546001600160a01b031633146100a55760405162461bcd60e51b815260206004820152601760248201527f4f6e6c79206f776e65722063616e207769746864726177000000000000000000604482015260640160405180910390fd5b6040516001600160a01b038216904780156108fc02915f818181858888f193505050501580156100d7573d5f803e3d5ffd5b5050565b5f602082840312156100eb575f80fd5b81356001600160a01b0381168114610101575f80fd5b939250505056fea2646970667358221220a4e28fd4be726a72d483cc4169239187dc8c796a78ea9082d83351bce65668c464736f6c63430008190033";

    public static final String FUNC_PAY = "pay";

    public static final String FUNC_WITHDRAW = "withdraw";

    @Deprecated
    protected PaymentManagerWrapper(String contractAddress, Web3j web3j, Credentials credentials, BigInteger gasPrice, BigInteger gasLimit) {
        super(BINARY, contractAddress, web3j, credentials, gasPrice, gasLimit);
    }

    protected PaymentManagerWrapper(String contractAddress, Web3j web3j, Credentials credentials, ContractGasProvider contractGasProvider) {
        super(BINARY, contractAddress, web3j, credentials, contractGasProvider);
    }

    @Deprecated
    protected PaymentManagerWrapper(String contractAddress, Web3j web3j, TransactionManager transactionManager, BigInteger gasPrice, BigInteger gasLimit) {
        super(BINARY, contractAddress, web3j, transactionManager, gasPrice, gasLimit);
    }

    protected PaymentManagerWrapper(String contractAddress, Web3j web3j, TransactionManager transactionManager, ContractGasProvider contractGasProvider) {
        super(BINARY, contractAddress, web3j, transactionManager, contractGasProvider);
    }

    public RemoteFunctionCall<TransactionReceipt> pay(BigInteger weiValue) {
        final Function function = new Function(
                FUNC_PAY, 
                Arrays.<Type>asList(), 
                Collections.<TypeReference<?>>emptyList());
        return executeRemoteCallTransaction(function, weiValue);
    }

    public RemoteFunctionCall<TransactionReceipt> withdraw(String _to) {
        final Function function = new Function(
                FUNC_WITHDRAW, 
                Arrays.<Type>asList(new org.web3j.abi.datatypes.Address(160, _to)), 
                Collections.<TypeReference<?>>emptyList());
        return executeRemoteCallTransaction(function);
    }

    @Deprecated
    public static PaymentManagerWrapper load(String contractAddress, Web3j web3j, Credentials credentials, BigInteger gasPrice, BigInteger gasLimit) {
        return new PaymentManagerWrapper(contractAddress, web3j, credentials, gasPrice, gasLimit);
    }

    @Deprecated
    public static PaymentManagerWrapper load(String contractAddress, Web3j web3j, TransactionManager transactionManager, BigInteger gasPrice, BigInteger gasLimit) {
        return new PaymentManagerWrapper(contractAddress, web3j, transactionManager, gasPrice, gasLimit);
    }

    public static PaymentManagerWrapper load(String contractAddress, Web3j web3j, Credentials credentials, ContractGasProvider contractGasProvider) {
        return new PaymentManagerWrapper(contractAddress, web3j, credentials, contractGasProvider);
    }

    public static PaymentManagerWrapper load(String contractAddress, Web3j web3j, TransactionManager transactionManager, ContractGasProvider contractGasProvider) {
        return new PaymentManagerWrapper(contractAddress, web3j, transactionManager, contractGasProvider);
    }

    public static RemoteCall<PaymentManagerWrapper> deploy(Web3j web3j, Credentials credentials, ContractGasProvider contractGasProvider) {
        return deployRemoteCall(PaymentManagerWrapper.class, web3j, credentials, contractGasProvider, BINARY, "");
    }

    public static RemoteCall<PaymentManagerWrapper> deploy(Web3j web3j, TransactionManager transactionManager, ContractGasProvider contractGasProvider) {
        return deployRemoteCall(PaymentManagerWrapper.class, web3j, transactionManager, contractGasProvider, BINARY, "");
    }

    @Deprecated
    public static RemoteCall<PaymentManagerWrapper> deploy(Web3j web3j, Credentials credentials, BigInteger gasPrice, BigInteger gasLimit) {
        return deployRemoteCall(PaymentManagerWrapper.class, web3j, credentials, gasPrice, gasLimit, BINARY, "");
    }

    @Deprecated
    public static RemoteCall<PaymentManagerWrapper> deploy(Web3j web3j, TransactionManager transactionManager, BigInteger gasPrice, BigInteger gasLimit) {
        return deployRemoteCall(PaymentManagerWrapper.class, web3j, transactionManager, gasPrice, gasLimit, BINARY, "");
    }
}
