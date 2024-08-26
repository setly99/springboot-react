

const Modal = ({isOpen, onClose, children}) => {
    if(!isOpen){
        return null;
    }
    return(
        <div>
            <div>
                <button onClick={onClose}>
                    들어가기
                </button>
            </div>
        </div>
    )

}
export default Modal;