// SPDX-License-Identifier: MIT
pragma solidity ^0.8.0;

contract PaymentManager {
    address owner;

    constructor() {
        owner = msg.sender;
    }

    function pay() external payable {
    }

    function withdraw(address payable _to) external {
        require(msg.sender == owner, "Only owner can withdraw");
        _to.transfer(address(this).balance);
    }
}
