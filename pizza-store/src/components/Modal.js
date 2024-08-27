import '../css/PizzaRouter.css';

const Modal = ({isOpen, onClose, children}) => {
    if(!isOpen){
        return null;
    }
    return(
        <div className='modal-container'>
            
                <button onClick={onClose}>
                    &times;
                </button>
                {children}
            
        </div>
    )

}
export default Modal;