import { shallow, configure } from 'enzyme';
import AddUser from '../components/add-user';
import { render, screen } from '@testing-library/react';
import Adapter from 'enzyme-adapter-react-16';
configure({adapter: new Adapter()});

test('add-user', () => {
    const component = shallow(<AddUser/>);
    const component1 = component.find('.add')
    expect(component1.length).toBe(1);
})


  
 